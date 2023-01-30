// Generated by view binder compiler. Do not edit!
package com.example.convertapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public final class OrderitemBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView date;

  @NonNull
  public final TextView order;

  @NonNull
  public final TextView orderdate;

  @NonNull
  public final TextView orderid;

  @NonNull
  public final TextView product;

  @NonNull
  public final TextView productorder;

  @NonNull
  public final TextView tname;

  @NonNull
  public final TextView username;

  private OrderitemBinding(@NonNull ConstraintLayout rootView, @NonNull TextView date,
      @NonNull TextView order, @NonNull TextView orderdate, @NonNull TextView orderid,
      @NonNull TextView product, @NonNull TextView productorder, @NonNull TextView tname,
      @NonNull TextView username) {
    this.rootView = rootView;
    this.date = date;
    this.order = order;
    this.orderdate = orderdate;
    this.orderid = orderid;
    this.product = product;
    this.productorder = productorder;
    this.tname = tname;
    this.username = username;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static OrderitemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static OrderitemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.orderitem, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static OrderitemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.date;
      TextView date = ViewBindings.findChildViewById(rootView, id);
      if (date == null) {
        break missingId;
      }

      id = R.id.order;
      TextView order = ViewBindings.findChildViewById(rootView, id);
      if (order == null) {
        break missingId;
      }

      id = R.id.orderdate;
      TextView orderdate = ViewBindings.findChildViewById(rootView, id);
      if (orderdate == null) {
        break missingId;
      }

      id = R.id.orderid;
      TextView orderid = ViewBindings.findChildViewById(rootView, id);
      if (orderid == null) {
        break missingId;
      }

      id = R.id.product;
      TextView product = ViewBindings.findChildViewById(rootView, id);
      if (product == null) {
        break missingId;
      }

      id = R.id.productorder;
      TextView productorder = ViewBindings.findChildViewById(rootView, id);
      if (productorder == null) {
        break missingId;
      }

      id = R.id.tname;
      TextView tname = ViewBindings.findChildViewById(rootView, id);
      if (tname == null) {
        break missingId;
      }

      id = R.id.username;
      TextView username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      return new OrderitemBinding((ConstraintLayout) rootView, date, order, orderdate, orderid,
          product, productorder, tname, username);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}