package com.sdg.commonlibrary.utils;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.sdg.commonlibrary.utils.MyLog;
//import com.gas.resources.dialog.LoadDialog;

/**
 * @Author sdg
 * 2020/4/3
 * @Description: Oss工具类
 */

public class OssUtils {

    private Context context;

    private OSS oss;
    private String endpoint = "";
    private String bucket = "";
    private String accessKeyId = "";
    private String accessKeySecret = "";
    private String securityToken = "";
//    private LoadDialog loadDialog;

    public OssUtils(Context context) {
        this.context = context;
//        loadDialog = new LoadDialog(context);
    }

    public OssUtils setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
        return this;
    }

    public OssUtils setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
        return this;
    }

    public OssUtils setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
        return this;
    }

    public OssUtils setEndPoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public OssUtils setBucket(String bucket) {
        this.bucket = bucket;
        return this;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public OssUtils build() {
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(accessKeyId, accessKeySecret, securityToken) {
            @Override
            public OSSFederationToken getFederationToken() {
                //可实现自动获取token
                return super.getFederationToken();
            }
        };
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        oss = new OSSClient(context, endpoint, credentialProvider, conf);
        return this;
    }

    public OSS getOss() {
        return this.oss;
    }

    /**
     * //     * @param appHost 应用服务器地址
     *
     * @param dir                   服务器文件路径
     * @param objectKey             上传到服务器后的名字
     * @param uploadFilePath        本地需要上传的文件路径
     * @param onAsyncUpLoadListener 上传回调监听
     */
    public void asyncUpLoad(final String dir, final String objectKey, String uploadFilePath, final OnAsyncUpLoadListener onAsyncUpLoadListener) {
        MyLog.e("文件服务器地址为：" + dir + objectKey + ",uploadFilePath = " + uploadFilePath);
        if (!TextUtils.isEmpty(objectKey) && !TextUtils.isEmpty(uploadFilePath) && oss != null) {
            // 构造上传请求
            PutObjectRequest put = null;
            put = new PutObjectRequest(bucket, dir + objectKey, uploadFilePath);

            // 异步上传时可以设置进度回调
            put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
                @Override
                public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                    if (onAsyncUpLoadListener != null) {
                        onAsyncUpLoadListener.onProgress(request, currentSize, totalSize);
                    }
                }
            });
            OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
                @Override
                public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                    if (onAsyncUpLoadListener != null) {
                        String directory = endpoint.replace("oss-cn", bucket + ".oss-cn") + "/";
                        onAsyncUpLoadListener.onSuccess(request, result, directory);
                    }
//                    loadDialog.dismiss();
                }

                @Override
                public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                    if (onAsyncUpLoadListener != null) {
                        onAsyncUpLoadListener.onFailure(request, clientExcepion, serviceException);
                    }
//                    loadDialog.dismiss();
                    // 请求异常
                    if (clientExcepion != null) {
                        // 本地异常如网络异常等
                        clientExcepion.printStackTrace();
                    }
                    if (serviceException != null) {
                        // 服务异常
                        MyLog.e("ErrorCode:" + serviceException.getErrorCode());
                        MyLog.e("RequestId:" + serviceException.getRequestId());
                        MyLog.e("HostId:" + serviceException.getHostId());
                        MyLog.e("RawMessage:" + serviceException.getRawMessage());
                    }
                }
            });
        }
    }

    public interface OnAsyncUpLoadListener {
        void onProgress(PutObjectRequest request, long currentSize, long totalSize);

        void onSuccess(PutObjectRequest request, PutObjectResult result, String directory);

        void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException);
    }

    /**
     * 判断文件是否存在
     *
     * @param remoteFileName
     * @return 2018年11月23日
     */
    public Boolean doesObjectExist(String remoteFileName) {
        boolean exist = false;
        try {
            exist = oss.doesObjectExist(bucket, remoteFileName);
            MyLog.e("exist：" + exist);
        } catch (ClientException e) {
            MyLog.e("判断失败ClientException：" + e.getMessage());
            e.printStackTrace();
        } catch (ServiceException e) {
            MyLog.e("判断失败ServiceException：" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            MyLog.e("判断失败 Exception：" + e.getMessage());
            e.printStackTrace();
        }
        return exist;
    }
}
