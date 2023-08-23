package com.example.httpClient.controller.okhttp;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import com.example.httpClient.model.Employee;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/okhttp")
public class OkhttpController {

    @Autowired
    private OkHttpClient okHttpClient;

    @GetMapping("/employees")
    public String getEmployees() throws IOException {
        Request request = new Request.Builder()
//                .url("https://dummy.restapiexample.com/api/v1/employees")
                .url("https://catfact.ninja/fact")
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @GetMapping("/employees/{id}")
    public String getEmployeeById(@PathVariable String id) throws IOException {
        Request request = new Request.Builder()
                .url("https://dummy.restapiexample.com/api/v1/employee/" + id)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @PostMapping("/create")
    public String createEmployee(@RequestBody Employee employee) throws IOException {
        okhttp3.RequestBody requestBody = new FormBody.Builder()
                .add("name", employee.getName())
                .add("salary", employee.getSalary())
                .add("age", employee.getAge())
                .build();

        Request request = new Request.Builder()
                .url("https://dummy.restapiexample.com/api/v1/create")
                .post(requestBody)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @PutMapping("/update/{id}")
    public String updateEmployee(@PathVariable String id, @RequestBody Employee employee) throws IOException {
        okhttp3.RequestBody requestBody = new FormBody.Builder()
                .add("name", employee.getName())
                .add("salary", employee.getSalary())
                .add("age", employee.getAge())
                .build();

        Request request = new Request.Builder()
                .url("https://dummy.restapiexample.com/api/v1/update/" + id)
                .put(requestBody)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable String id) throws IOException {
        Request request = new Request.Builder()
                .url("https://dummy.restapiexample.com/api/v1/delete/" + id)
                .delete()
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }
}