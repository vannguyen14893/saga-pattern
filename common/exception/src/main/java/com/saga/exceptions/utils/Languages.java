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
        return messages.get(key);
    }
}
