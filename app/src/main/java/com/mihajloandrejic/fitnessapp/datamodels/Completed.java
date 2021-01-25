package com.mihajloandrejic.fitnessapp.datamodels;

import com.mihajloandrejic.fitnessapp.helper.Type;

public class Completed  implements Type {

    boolean isComplited;

    public Completed(boolean isComplited) {
        this.isComplited = isComplited;
    }

    public boolean isComplited() {
        return isComplited;
    }

    @Override
    public int getType() {
        return 4;
    }
}
