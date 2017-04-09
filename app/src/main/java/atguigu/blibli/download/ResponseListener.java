package atguigu.blibli.download;

/**
 * Created by 陈江峰 on 2017/4/7.
 */

public interface ResponseListener {
    /**
     * @param progress 已经下载或上传字节数
     * @param total    总字节数
     * @param done     是否完成
     */
    void onProgress(long progress, long total, boolean done);

    void onResponse();

    void onFailure(String error);
}
