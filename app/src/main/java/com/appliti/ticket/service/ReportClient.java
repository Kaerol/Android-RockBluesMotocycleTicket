package com.appliti.ticket.service;

import android.app.Activity;
import android.widget.Toast;

import com.appliti.ticket.activity.ReportActivity;
import com.appliti.ticket.service.response.Report;
import com.kodart.httpzoid.HttpResponse;
import com.kodart.httpzoid.NetworkError;
import com.kodart.httpzoid.ResponseHandler;

public class ReportClient extends RBiMHttpRequest {
    public static String ORDER_CODE_DATA = "ORDER_CODE_DATA";

    public ReportClient(final Activity activity) {
        super(activity);
    }

    public void get() {
        http.get(getShopUrl() + "/raport.php")
                .header("Authorization", RBiMHttpRequest.authorization)
                .handler(new ResponseHandler<Report>() {
                    @Override
                    public void success(final Report report, final HttpResponse response) {
                        if (response.getCode() == 200) {
                            ((ReportActivity) getActivity()).setReport(report);
                        } else {
                            Toast.makeText(getActivity(), "error ", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void error(final String message, final HttpResponse response) {
                        Toast.makeText(getActivity(), "error ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(final NetworkError error) {
                        Toast.makeText(getActivity(), "failure ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void complete() {
                        ((ReportActivity) getActivity()).initView();
                    }
                }).send();
    }

    private static class CheckCodeData {
        private final int order_id;

        private CheckCodeData(int order_id) {
            this.order_id = order_id;
        }
    }
}
