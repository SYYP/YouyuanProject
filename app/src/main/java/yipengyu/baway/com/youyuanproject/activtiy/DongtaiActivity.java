package yipengyu.baway.com.youyuanproject.activtiy;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;

import de.hdodenhof.circleimageview.CircleImageView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import yipengyu.baway.com.youyuanproject.Presenter.DongtaiPresenter;
import yipengyu.baway.com.youyuanproject.R;
import yipengyu.baway.com.youyuanproject.View.DongtaiView;
import yipengyu.baway.com.youyuanproject.base.BaseMvpActivity;
import yipengyu.baway.com.youyuanproject.base.IActivity;
import yipengyu.baway.com.youyuanproject.base.IApplication;
import yipengyu.baway.com.youyuanproject.bean.UploadPhotoBean;
import yipengyu.baway.com.youyuanproject.utils.Constants;
import yipengyu.baway.com.youyuanproject.utils.ImageResizeUtils;
import yipengyu.baway.com.youyuanproject.utils.PreferencesUtils;
import yipengyu.baway.com.youyuanproject.utils.SDCardUtils;
import yipengyu.baway.com.youyuanproject.widget.MyToast;

import static yipengyu.baway.com.youyuanproject.utils.ImageResizeUtils.copyStream;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */
@RuntimePermissions
public class DongtaiActivity extends BaseMvpActivity<DongtaiView,DongtaiPresenter> implements View.OnClickListener,DongtaiView {
    static final int INTENTFORCAMERA = 1 ;
    static final int INTENTFORPHOTO = 2 ;
    public String LocalPhotoName;
    private ImageView img_pai;
    private CircleImageView touxiang;
    public String createLocalPhotoName() {
        LocalPhotoName = System.currentTimeMillis() + "face.jpg";
        return  LocalPhotoName ;
    }

    @Override
    public DongtaiPresenter ininpresenter() {
        return new DongtaiPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_3_dongtai);
         ininview();
    }
    /**
     * 打开系统相机
     */
    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA} )
    public void toCamera(){
        try {
            Intent intentNow = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intentNow.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(SDCardUtils.getMyFaceFile(createLocalPhotoName())));
            startActivityForResult(intentNow, INTENTFORCAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @OnShowRationale({Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA})
    public void showRationaleForCamera(final PermissionRequest request){

        new android.app.AlertDialog.Builder(this)
                .setMessage("需要打开您的相机来上传照片并保存照片")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        request.proceed();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .show();
    }

    @OnPermissionDenied({Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA})
    public void onDenied(){
        Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();

    }



    @OnNeverAskAgain({Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA})
    public void onNeverAsyAgain(){
        Toast.makeText(this, "不再提示", Toast.LENGTH_SHORT).show();
    }


    /**
     * 打开相册
     */
    public void toPhoto(){
        try {
            createLocalPhotoName();
            Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
            getAlbum.setType("image/*");
            startActivityForResult(getAlbum, INTENTFORPHOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ininview() {
        img_pai = (ImageView) findViewById(R.id.paizhao);
        touxiang = (CircleImageView) findViewById(R.id.touxiang);
        img_pai.setOnClickListener(this);
        String photo = PreferencesUtils.getValueByKey(DongtaiActivity.this, "photo", "");
       Glide.with(this).load(photo).error(R.drawable.a).into(touxiang);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.paizhao:
                final CharSequence[] items = {"照相", "相册选取", };
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("上传形象照");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                       if(item==0){
                            toCamera();
                       }
                        else {
                           toPhoto();
                       }
                    }
                });
                builder.show();
                break;
        }

    }
    private void opencrema() {
 DongtaiActivityPermissionsDispatcher.toCameraWithCheck(this);
      //----------
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //----------
        DongtaiActivityPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }

    @Override
    public void onSuccess(UploadPhotoBean uploadPhotoBean) {
        if(uploadPhotoBean.getResult_code() == 200){
            MyToast.makeText(IApplication.getApplication(),"上传成功",Toast.LENGTH_SHORT);
        }
        // AppManager.getAppManager().finishActivity(PhotoActivity.class);
    }

    @Override
    public void onFailed(int code) {
        MyToast.makeText(IApplication.getApplication(),""+code,Toast.LENGTH_SHORT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case INTENTFORPHOTO:
                //相册
                try {
                    // 必须这样处理，不然在4.4.2手机上会出问题
                    Uri originalUri = data.getData();
                    File f = null;
                    if (originalUri != null) {
                        f = new File(SDCardUtils.photoCacheDir, LocalPhotoName);
                        String[] proj = {MediaStore.Images.Media.DATA};
                        Cursor actualimagecursor = this.getContentResolver().query(originalUri, proj, null, null, null);
                        if (null == actualimagecursor) {
                            if (originalUri.toString().startsWith("file:")) {
                                File file = new File(originalUri.toString().substring(7, originalUri.toString().length()));
                                if (!file.exists()) {
                                    //地址包含中文编码的地址做utf-8编码
                                    originalUri = Uri.parse(URLDecoder.decode(originalUri.toString(), "UTF-8"));
                                    file = new File(originalUri.toString().substring(7, originalUri.toString().length()));
                                }
                                FileInputStream inputStream = new FileInputStream(file);
                                FileOutputStream outputStream = new FileOutputStream(f);
                                copyStream(inputStream, outputStream);
                            }
                        } else {
                            // 系统图库
                            int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            actualimagecursor.moveToFirst();
                            String img_path = actualimagecursor.getString(actual_image_column_index);
                            if (img_path == null) {
                                InputStream inputStream = this.getContentResolver().openInputStream(originalUri);
                                FileOutputStream outputStream = new FileOutputStream(f);
                                copyStream(inputStream, outputStream);
                            } else {
                                File file = new File(img_path);
                                FileInputStream inputStream = new FileInputStream(file);
                                FileOutputStream outputStream = new FileOutputStream(f);
                                copyStream(inputStream, outputStream);
                            }

                        }
                        Bitmap bitmap = ImageResizeUtils.resizeImage(f.getAbsolutePath(), Constants.RESIZE_PIC);
                        FileOutputStream fos = new FileOutputStream(f.getAbsolutePath());
                        if (bitmap != null) {

                            //----------sharedprefencebaocunneirong-----------
                            PreferencesUtils.addConfigInfo(this, "xwidth", bitmap.getWidth() + "");
                            System.out.print("------------" + bitmap.getWidth());
                            PreferencesUtils.addConfigInfo(this, "xheight", bitmap.getHeight() + "");
                            System.out.print("--s-------" + bitmap.getHeight());
                            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos)) {
                                fos.close();
                                fos.flush();
                            }
                            if (!bitmap.isRecycled()) {
                                bitmap.isRecycled();
                            }
                            presenter.getdatafile(f);


                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }


                break;
            case INTENTFORCAMERA:
//                相机
                try {

                    //file 就是拍照完 得到的原始照片
                    File file = new File(SDCardUtils.photoCacheDir, LocalPhotoName);

                    Bitmap bitmap = ImageResizeUtils.resizeImage(file.getAbsolutePath(), Constants.RESIZE_PIC);

                    FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());
                    if (bitmap != null) {

                        PreferencesUtils.addConfigInfo(this, "picwidth", bitmap.getWidth() + "");
                        PreferencesUtils.addConfigInfo(this, "picheight", bitmap.getHeight() + "");

                        if (bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos)) {
                            fos.close();
                            fos.flush();
                        }
                        if (!bitmap.isRecycled()) {
                            //通知系统 回收bitmap
                            bitmap.isRecycled();
                        }
                        presenter.getdatafile(file);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;
        }
    }
}
