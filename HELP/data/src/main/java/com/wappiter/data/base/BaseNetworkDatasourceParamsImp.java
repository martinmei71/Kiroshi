package com.wappiter.data.base;

import com.wappiter.domain.base.Mapper;
import com.wappiter.domain.base.Model;
import com.wappiter.domain.base.datasource.DatasourceParams;
import com.wappiter.domain.base.globalexception.ApiException;
import com.wappiter.domain.base.globalexception.MapperException;
import com.wappiter.domain.base.globalexception.NetworkException;
import com.wappiter.domain.base.globalexception.TimeoutException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Response;

public abstract class BaseNetworkDatasourceParamsImp<Values extends DatasourceParams, ApiResponse extends ApiModel, ModelObject extends Model>
        extends BaseNetworkDatasource {

    private Mapper<ApiResponse, ModelObject> mapper;

    protected abstract Call<ApiResponse> run(Values datasourceParams);

    public BaseNetworkDatasourceParamsImp() {

    }

    public BaseNetworkDatasourceParamsImp(Mapper<ApiResponse, ModelObject> mapper) {

        this.mapper = mapper;
    }

    protected ModelObject execute(Values params) throws NetworkException, ApiException, MapperException {

        Response<ApiResponse> response;
        try {
            response = run(params).execute();

            /** Returns true if {@link #code()} is in the range [200..300). */
            if (response.isSuccessful()) {
                if (mapper != null) {
                    return mapper.map(response.body());
                }
                return null;
            } else {
                handleError(response);
                return null;
            }

        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            throw new TimeoutException();
        } catch (IOException e) {
            e.printStackTrace();
            throw new NetworkException();
        }
    }
}
