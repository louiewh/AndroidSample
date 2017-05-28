package com.dawang.androidexample.rxjava;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by louie.wang on 2017/5/10.
 */

public class RetrofitDemo {
    static final  String API = "https://api.github.com";

    static Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(API)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    static Retrofit mRetrofitRx = new Retrofit.Builder()
            .baseUrl(API)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    GitHubService getGitHubService(){
        GitHubService service = mRetrofit.create(GitHubService.class);

        return  service;
    }

    GithubUser getGithubUserSync(String name){
        GitHubService service = getGitHubService();
        GithubUser user = null;
        Call<GithubUser> call = service.getFeed(name);
        try {
            user = call.execute().body();
            logRetrofit(" getGithubUserSync:" + new Gson().toJson(user));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    void getGithubUserAsync(String name){
        GitHubService service = getGitHubService();
        Call<GithubUser> call = service.getFeed(name);

        call.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                GithubUser user = response.body();
                String result = new GsonBuilder().create().toJson(user);

                logRetrofit(" getGithubUserAsync:" + result);
            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {

            }
        });
    }

    List<GithubRepo> getRepoList(String name){
        GitHubService service = getGitHubService();
        Call<List<GithubRepo>>  call = service.listRepos(name);
        List<GithubRepo> listRepo = null;
        try {
            listRepo = call.execute().body();
            for (GithubRepo repo:listRepo) {
                logRetrofit(" getRepoList Repo:" + new Gson().toJson(repo));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listRepo;
    }

    public void getRepoListByRxJava(String name){
        GitHubService service = mRetrofitRx.create(GitHubService.class);
        service.listReposByRx(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<GithubRepo>>() {
                    @Override
                    public void accept(@NonNull List<GithubRepo> githubRepos) throws Exception {
                        for (GithubRepo repo:githubRepos) {
                            logRetrofit(" getRepoListByRxJava Repo:" + new Gson().toJson(repo));
                        }
                    }
                });
    }

    void logRetrofit(String param){
        Log.e("RetrofitDemo", "Pid:"+Thread.currentThread().getId() +"||"+ param);
    }

    void doAsyncTask(){
        AsyncTask<Void, Void, Void>  asyncTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                getGithubUserSync("octocat");
                getGithubUserAsync("octocat");
                getRepoList("octocat");
                return null;
            }
        };


        asyncTask.execute();
    }

    interface GitHubService {

        @GET("/users/{user}")      // here is the other url part.best way is to start using /
        public Call<GithubUser> getFeed(@Path("user") String user);
        // string user is for passing values from edittext for eg: user=basil2style,google
        // response is the response from the server which is now in the POJO

        @GET("users/{user}/repos")
        Call<List<GithubRepo>> listRepos(@Path("user") String user);

        @GET("users/{user}/repos")
        Observable<List<GithubRepo>> listReposByRx(@Path("user") String user);
    }
}
