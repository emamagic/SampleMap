package com.geo.sampleapplication.ui.fragment.pageone;


import androidx.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
        InputType.INPUT_EMPTY,
        InputType.INPUT_INVALID
})
public @interface InputType {
    String INPUT_EMPTY = "lat and lon should not be empty";
    String INPUT_INVALID = "lat and lon should be inside of range";
}
