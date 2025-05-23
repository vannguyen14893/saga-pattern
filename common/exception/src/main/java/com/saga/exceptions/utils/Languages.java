package com.saga.exceptions.utils;

import java.util.HashMap;
import java.util.Map;

public class Languages {
    public static String messagesVn(String key) {
        Map<String, String> messages = new HashMap<>();
        messages.put("phone.valid", "Số điện thoại không đúng định dạng");
        messages.put("password.valid", "Mật khẩu không hợp lệ");
        messages.put("email.valid", "Email không hợp lệ");
        messages.put("fullName.not.blank", "Tên không được để trống");
        messages.put("code.qr.valid", "Mã không đúng định dạng");
        messages.put("locked","Tài khoản đã bị khóa");
        messages.put("enable","Tài khoản chưa kích hoạt");
        messages.put("expired","Tài khoản đã bị hết hạn");
        messages.put("AbstractUserDetailsAuthenticationProvider.badCredentials","password_valid");
        messages.put("password_valid","Mật khẩu không đúng");
        messages.put("message.badCredentials","Mật khẩu không đúng");
        messages.put("max_session","Tài khoản này đã được đăng nhập từ một thiết bị khác!");
        messages.put("session_expired","Phiên đăng nhập hết hạn");
        messages.put("user_not_found","Tài khoản không tồn tại");
        messages.put("201","Tạo mới thành công");
        messages.put("200","Thành công");
        messages.put("400","Tạo mới thất bại");
        messages.put("500","Hệ thống xảy ra lỗi");
        messages.put("date.format","dd/MM/yyyy");
        messages.put("invalid_token","Mã không hợp lệ");
        messages.put("password_no_match","Các trường mật khẩu phải khớp nhau");
        messages.put("expired_token","Mã hết hạn");
        messages.put("client_not_found","Mã người dùng không tồn tại");
        messages.put("forgot_password_success","Nếu địa chỉ email của bạn tồn tại trong cơ sở dữ liệu của chúng tôi, bạn sẽ nhận được liên kết khôi phục mật khẩu tại địa chỉ email của mình sau vài phút.");
        messages.put("404", "%s không tồn tại!");
        messages.put("403", "Tài khoản của bạn không có quyền truy cập!");
        messages.put("token_not_found", "Token không tồn tại!");
        messages.put("token_expired", "Token hết hạn!");
        messages.put("save_password_success","Thay đổi mật khẩu thành công");
        messages.put("clientId.not.blank", "ClientId không được để trống");
        messages.put("clientSecret.not.blank", "Client Secret không được để trống");
        messages.put("grantType.not.blank", "Grant type không được để trống");
        messages.put("grant_types_not_found","Grant type không tồn tại");
        messages.put("otp_invalid","Mã otp không hợp lệ");
        messages.put("otp_expired","Mã otp hết hạn");
        return messages.get(key);
    }
}
