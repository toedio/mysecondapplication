package com.example.mysecondapplication.helper;

public class RunOnSuccessCallback extends SimpleCallback{

    private Runnable runnable;

    @Override
    public void call() {
        if(super.success)
            runnable.run();
    }

    public RunOnSuccessCallback runOnSuccess(Runnable runnable) {
        this.runnable = runnable;
        return this;
    }
}
