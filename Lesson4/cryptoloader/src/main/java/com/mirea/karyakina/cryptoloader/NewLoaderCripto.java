package com.mirea.karyakina.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class NewLoaderCripto extends AsyncTaskLoader<String>{

    public static final String ARG_WORD="proposal";
    public static final String ARG_KEY="keyproposal";
    private byte[] proposal;
    private byte[] keyproposal;


    public NewLoaderCripto(@NonNull Context context, Bundle args){
        super(context);
        if(args	!=	null) {
            proposal = args.getByteArray(ARG_WORD);
            keyproposal = args.getByteArray(ARG_KEY);
        }
    }

    @Override
    public void onStartLoading(){
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public	String	loadInBackground()	{
        SystemClock.sleep(500);
        SecretKey originalKey = new SecretKeySpec(keyproposal, 0,
                keyproposal.length, "AES");
        return decryptMsg(proposal, originalKey);
    }

    public static String decryptMsg(byte[] cipherText, SecretKey secret){
        try	{
            Cipher cipher =	Cipher.getInstance( "AES");
            cipher.init(Cipher.DECRYPT_MODE, secret);
            return	new	String(cipher.doFinal(cipherText));
        }catch(NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
               | BadPaddingException | InvalidKeyException e){
            throw new RuntimeException(e);
        }
    }
}
