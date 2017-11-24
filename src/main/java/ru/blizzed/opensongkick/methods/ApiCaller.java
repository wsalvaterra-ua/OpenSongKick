package ru.blizzed.opensongkick.methods;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.blizzed.opensongkick.ApiCallException;
import ru.blizzed.opensongkick.ApiResponseException;
import ru.blizzed.opensongkick.OpenSongKickContext;
import ru.blizzed.opensongkick.models.Error;

import java.io.IOException;

public class ApiCaller<ResultType> {

    public interface Listener<ResultType> {
        default void onComplete(ResultType result, ApiCaller<ResultType> apiCaller) {
        }
        default void onError(Error error, ApiCaller<ResultType> apiCaller) {
        }
        default void onFailure(ApiCallException e, ApiCaller<ResultType> apiCaller) {
        }
    }

    private Call<ResultType> call;

    ApiCaller(Call<ResultType> call) {
        this.call = call;
    }

    public ResultType execute() throws ApiCallException, ApiResponseException {
        try {
            Response<ResultType> response = call.execute();
            if (response.isSuccessful())
                return response.body();
            else throw new ApiResponseException(parseError(response));
        } catch (IOException e) {
            throw new ApiCallException(e);
        }
    }

    public void execute(Listener<ResultType> listener) {
        call.enqueue(new Callback<ResultType>() {
            @Override
            public void onResponse(Call<ResultType> call, Response<ResultType> response) {
                if (response.isSuccessful()) listener.onComplete(response.body(), ApiCaller.this);
                else  {
                    try {
                        listener.onError(parseError(response), ApiCaller.this);
                    } catch (IOException e) {
                        listener.onFailure(new ApiCallException(e), ApiCaller.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultType> call, Throwable throwable) {
                listener.onFailure(new ApiCallException(throwable), ApiCaller.this);
            }
        });
    }

    private Error parseError(Response response) throws IOException {
        return OpenSongKickContext.getInstance().parseError(response.errorBody());
    }

}
