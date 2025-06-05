package com.mirea.karyakina.cryptoloader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.mirea.karyakina.cryptoloader.databinding.ActivityMainBinding;

import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String>{
    public final String TAG	= this.getClass().getSimpleName();
    private	final int LoaderID=	1278;
    private ActivityMainBinding binding;
    private SecretKey secretKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        secretKey = generate();



        binding.btnCrptoLoader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cr_proposal = binding.edCrptoLoader.getText().toString();
                byte[] encryptedText = encryptMsg(cr_proposal, secretKey);
                Bundle bundle = new Bundle();
                bundle.putByteArray(NewLoaderCripto.ARG_WORD,encryptedText);
                bundle.putByteArray(NewLoaderCripto.ARG_KEY, secretKey.getEncoded());
                LoaderManager.getInstance(MainActivity.this).initLoader(LoaderID,bundle,
                            MainActivity.this);
            }
        });

    }

    public static SecretKey	generate(){
        try	{
            SecureRandom sr	= SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("any	data used as random	seed".getBytes());
            KeyGenerator kg	= KeyGenerator.getInstance("AES");
            kg.init(256,sr);
            return new SecretKeySpec((kg.generateKey()).getEncoded(),"AES");
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    public static byte[] encryptMsg(String message, SecretKey secret){
        Cipher cipher = null;
        try	{
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,secret);
            return cipher.doFinal(message.getBytes());
        }catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
               BadPaddingException | IllegalBlockSizeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader)	{
        Log.d(TAG,"onLoaderReset");
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i,	@Nullable Bundle bundle){
        if	(i == LoaderID)	{
            Toast.makeText(this,"onCreateLoader: "+i, Toast.LENGTH_LONG).show();
            return new NewLoaderCripto(this,	bundle);
        }
        throw	new InvalidParameterException("Invalid loader id");
    }

    @Override
    public void onLoadFinished(@NonNull	Loader<String> loader,	String s)	{
        if	(loader.getId()	==	LoaderID)	{
            Toast.makeText(this,"Результат:" + s, Toast.LENGTH_LONG).show();
        }
    }

}