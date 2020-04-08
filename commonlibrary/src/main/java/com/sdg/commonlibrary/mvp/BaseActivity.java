package com.sdg.commonlibrary.mvp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sdg.commonlibrary.R;
import com.sdg.commonlibrary.utils.MyUtils;

import java.util.List;

/**
 * activity基类
 * @author sdg
 * 2020/4/3
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected Activity baseContext;
    protected T mPresenter;
    protected Bundle bundle;
    private LoadingUtil loadingUtil;
    private long mExitTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        bundle = savedInstanceState;
        baseContext = this;
        createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        setStatusBar();
        initView();
        initData();
    }

    public void setStatusBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).navigationBarColor(R.color.color_black).init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoading();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        MyUtils.fixInputMethodManagerLeak(this);//解决InputMethodManager内存泄露现象
    }

    /**
     * 布局
     * */
    protected abstract int getLayout();

    /**
     * presenter
     * */
    protected abstract void createPresenter();

    /**
     * 初始化控件
     * */
    protected abstract void initView();

    /**
     * 初始化数据
     * */
    protected abstract void initData();

    /**
     * 下拉刷新 如有需要及方法内添加刷新数据即可
     * */
    protected abstract void refreshTodo();

    /**
     * 绑定 smartRefreshLayout
     * @param smartRefreshLayout
     * desc:使用时在布局内用 SmartRefreshLayout 包裹住所需要刷新的列表
     * 需要在你的activity里面的initdata方法调用此方法
     * 默认1500ms
     * */
    //TODO 待迭代 可让用户自行设置刷新布局
    public void baseAcSmartRefresh(SmartRefreshLayout smartRefreshLayout) {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshTodo();
                refreshLayout.finishRefresh(1500);
            }
        });
    }

    /**
     * 绑定 smartRefreshLayout
     * @param smartRefreshLayout
     * desc:使用时在布局内用 SmartRefreshLayout 包裹住所需要刷新的列表
     * 需要在你的activity里面的initdata方法调用此方法
     * @param time 刷新的延时时间
     * */
    public void baseAcSmartRefresh(SmartRefreshLayout smartRefreshLayout, final int time) {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshTodo();
                refreshLayout.finishRefresh(time);
            }
        });
    }

    public void setStatusBarTranslucent(String color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            // Translucent status bar
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }

    /**
     * 打开一个Activity 默认 不关闭当前activity
     * @param clz activity
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    public void gotoActivity(Class<?> clz, Bundle dle) {
        gotoActivity(clz, false, dle);
    }

    /**
     * 打开一个Activity
     * @param clz activity
     * @param isCloseCurrentActivity 是否关闭当前activity
     */
    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    /**
     * 打开一个Activity
     * @param clz activity
     * @param isCloseCurrentActivity 是否关闭当前activity
     * @param ex 携带bundle数据
     */
    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle ex) {
        Intent intent = new Intent(this, clz);
        if (ex != null)
            intent.putExtras(ex);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            finish();
        }
    }

    /**
     * 关闭当前activity
     * */
    public void finishActivity() {
        baseContext.finish();
    }

    /**
     * 关闭指定的activity
     * */
    public void finishActivity(Class<?> clz) {
        ActivityUtils.finishActivity((Class<? extends Activity>) clz);
    }

    /**
     * 显示进度条
     */
    //TODO 添加handler内显示和停止动画 避免数据过大时动画卡顿
    public void showLoading() {
        if (loadingUtil == null) {
            loadingUtil = new LoadingUtil(this);
        }
        loadingUtil.show();
    }

    /**
     * 显示文本信息进度条
     */
    public void showTextLoading(String text) {
        if (loadingUtil == null) {
            loadingUtil = new LoadingUtil(this);
        }
        loadingUtil.setText(text);
        loadingUtil.show();
    }

    /**
     * 隐藏进度条
     */
    public void hideLoading() {
        if (loadingUtil != null) {
            loadingUtil.hide();
        }
    }

    /**
     * 给每个页面添加include的时候，对于左中右的控件时间处理
     * 一般左边是返回、中间是页面名、右边是更多
     * */
    //TODO 待完善初始化头部
    public void setTitleAndBack(TextView tv, String title, ImageView ivBack) {
        if (tv != null) {
            tv.setText(title == null ? "" : title);
        }
        if (ivBack != null) {
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    public void setBackClick(ImageView ivBack) {
        if (ivBack != null) {
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    /**
     * 退出app
     * @param intervalTime 两次点击的间隔时间
     * */
    public void exit(long intervalTime) {
        if ((System.currentTimeMillis() - mExitTime) > intervalTime) {
            showShort("再按一次退出应用");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    /**
     * 获取权限
     * @param permissions N个权限
     * */
    private void getPermission(String... permissions) {
        XXPermissions.with(this)
                .permission(permissions)
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
//                            showShortToast("获取权限成功");
                        } else {
//                            ToastUtils.showShort("获取权限成功，部分权限未正常授予");
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (quick) {
                            finishActivity();
                            showShort("被永久拒绝授权，请手动授予权限");
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.gotoPermissionSettings(baseContext);
                        } else {
                            finishActivity();
                            showShort("获取权限失败");
                        }
                    }
                });
    }

    /**
     * 短吐司
     * @param msg 吐司内容
     * */
    public void showShort(String msg){
        ToastUtils.showShort(msg);
    }

    /**
     * 长吐司
     * @param msg 吐司内容
     * */
    public void showLong(String msg){
        ToastUtils.showLong(msg);
    }

}