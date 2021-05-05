package com.wappiter.data.base;

import com.wappiter.domain.base.Mapper;
import com.wappiter.domain.base.Model;
import com.wappiter.domain.base.datasource.VoidDatasourceParams;
import com.wappiter.domain.base.globalexception.ApiException;
import com.wappiter.domain.base.globalexception.MapperException;
import com.wappiter.domain.base.globalexception.NetworkException;

import retrofit2.Call;

/**
 * Created by porta on 21/08/2017.
 */

public abstract class BaseNetworkDatasourceImp<ApiResponse extends ApiModel, Success extends Model>
        extends BaseNetworkDatasourceParamsImp<VoidDatasourceParams, ApiResponse, Success> {

    protected abstract Call<ApiResponse> run();

    public BaseNetworkDatasourceImp() {

    }

    public BaseNetworkDatasourceImp(Mapper<ApiResponse, Success> mapper) {

        super(mapper);
    }

    @Override
    protected Call<ApiResponse> run(VoidDatasourceParams aVoid) {

        return run();
    }

    protected Success execute() throws NetworkException, ApiException, MapperException {

        return execute(null);
    }
}
