package com.movienetscape.appmanagementservice.service.contract;


import com.movienetscape.appmanagementservice.dto.response.AppDataResponse;

public interface AppDataService {

    AppDataResponse getAppData(int page, int size);
}
