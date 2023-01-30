// Generated by view binder compiler. Do not edit!
package com.example.convertapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.convertapp.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ProductitemBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CircleImageView buy;

  @NonNull
  public final TextView edtname;

  @NonNull
  public final TextView edtprice;

  @NonNull
  public final ImageView imageView4;

  @NonNull
  public final TextView quttt;

  @NonNull
  public final TextView tname;

  @NonNull
  public final TextView tprice;

  @NonNull
  public final TextView tquantaty;

  private ProductitemBinding(@NonNull ConstraintLayout rootView, @NonNull CircleImageView buy,
      @NonNull TextView edtname, @NonNull TextView edtprice, @NonNull ImageView imageView4,
      @NonNull TextView quttt, @NonNull TextView tname, @NonNull TextView tprice,
      @NonNull TextView tquantaty) {
    this.rootView = rootView;
    this.buy = buy;
    this.edtname = edtname;
    this.edtprice = edtprice;
    this.imageView4 = imageView4;
    this.quttt = quttt;
    this.tname = tname;
    this.tprice = tprice;
    this.tquantaty = tquantaty;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ProductitemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ProductitemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.productitem, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ProductitemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buy;
      CircleImageView buy = ViewBindings.findChildViewById(rootView, id);
      if (buy == null) {
        break missingId;
      }

      id = R.id.edtname;
      TextView edtname = ViewBindings.findChildViewById(rootView, id);
      if (edtname == null) {
        break missingId;
      }

      id = R.id.edtprice;
      TextView edtprice = ViewBindings.findChildViewById(rootView, id);
      if (edtprice == null) {
        break missingId;
      }

      id = R.id.imageView4;
      ImageView imageView4 = ViewBindings.findChildViewById(rootView, id);
      if (imageView4 == null) {
        break missingId;
      }

      id = R.id.quttt;
      TextView quttt = ViewBindings.findChildViewById(rootView, id);
      if (quttt == null) {
        break missingId;
      }

      id = R.id.tname;
      TextView tname = ViewBindings.findChildViewById(rootView, id);
      if (tname == null) {
        break missingId;
      }

      id = R.id.tprice;
      TextView tprice = ViewBindings.findChildViewById(rootView, id);
      if (tprice == null) {
        break missingId;
      }

      id = R.id.tquantaty;
      TextView tquantaty = ViewBindings.findChildViewById(rootView, id);
      if (tquantaty == null) {
        break missingId;
      }

      return new ProductitemBinding((ConstraintLayout) rootView, buy, edtname, edtprice, imageView4,
          quttt, tname, tprice, tquantaty);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
