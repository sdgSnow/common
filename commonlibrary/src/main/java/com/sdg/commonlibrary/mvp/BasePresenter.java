package com.sdg.commonlibrary.mvp;

/**
 * presenter 基类
 * @author sdg
 * 2020/4/3
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
