package com.movienetscape.appmanagementservice;


import com.movienetscape.appmanagementservice.controller.MovieControllerTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@SuiteDisplayName("Movie Test Suite")
@SpringBootTest
@SelectClasses({
        MovieControllerTest.class
})
public class MovieTestSuite {
}
