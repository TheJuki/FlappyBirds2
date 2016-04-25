package com.nargles.flappybird2.ui.TweenAccessors;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * Value Accessor
 * Copyright 2016 Nargles.
 *
 * @author Justin Kirk (Project Manager)
 * @version 1.0
 */
public class ValueAccessor implements TweenAccessor<Value> {

    @Override
    public int getValues(Value target, int tweenType, float[] returnValues) {
        returnValues[0] = target.getValue();
        return 1;
    }

    @Override
    public void setValues(Value target, int tweenType, float[] newValues) {
        target.setValue(newValues[0]);
    }

}
