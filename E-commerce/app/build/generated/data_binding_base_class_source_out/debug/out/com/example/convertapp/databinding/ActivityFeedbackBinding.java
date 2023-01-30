// Generated by view binder compiler. Do not edit!
package com.example.convertapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.convertapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityFeedbackBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView contactFormTitle;

  @NonNull
  public final EditText message;

  @NonNull
  public final EditText name;

  @NonNull
  public final TextView rating;

  @NonNull
  public final RatingBar simpleRatingBar;

  @NonNull
  public final Button submit;

  private ActivityFeedbackBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView contactFormTitle, @NonNull EditText message, @NonNull EditText name,
      @NonNull TextView rating, @NonNull RatingBar simpleRatingBar, @NonNull Button submit) {
    this.rootView = rootView;
    this.contactFormTitle = contactFormTitle;
    this.message = message;
    this.name = name;
    this.rating = rating;
    this.simpleRatingBar = simpleRatingBar;
    this.submit = submit;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFeedbackBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFeedbackBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_feedback, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFeedbackBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.contact_form_title;
      TextView contactFormTitle = ViewBindings.findChildViewById(rootView, id);
      if (contactFormTitle == null) {
        break missingId;
      }

      id = R.id.message;
      EditText message = ViewBindings.findChildViewById(rootView, id);
      if (message == null) {
        break missingId;
      }

      id = R.id.name;
      EditText name = ViewBindings.findChildViewById(rootView, id);
      if (name == null) {
        break missingId;
      }

      id = R.id.rating;
      TextView rating = ViewBindings.findChildViewById(rootView, id);
      if (rating == null) {
        break missingId;
      }

      id = R.id.simpleRatingBar;
      RatingBar simpleRatingBar = ViewBindings.findChildViewById(rootView, id);
      if (simpleRatingBar == null) {
        break missingId;
      }

      id = R.id.submit;
      Button submit = ViewBindings.findChildViewById(rootView, id);
      if (submit == null) {
        break missingId;
      }

      return new ActivityFeedbackBinding((ConstraintLayout) rootView, contactFormTitle, message,
          name, rating, simpleRatingBar, submit);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
