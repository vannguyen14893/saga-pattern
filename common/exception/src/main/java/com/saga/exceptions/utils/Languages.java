package com.saga.exceptions.utils;

import java.util.HashMap;
import java.util.Map;

public class Languages {
    public static String messagesVn(String key) {
        Map<String, String> messages_en = new HashMap<>();
        messages_en.put("phone.valid", "Số điện thoại không đúng định dạng");
        messages_en.put("password.valid", "Mật khẩu không hợp lệ");
        messages_en.put("email.valid", "Email không hợp lệ");
        messages_en.put("fullName.not.blank", "Tên không được để trống");
        messages_en.put("code.qr.valid", "Mã không đúng định dạng");
        messages_en.put("locked", "Tài khoản đã bị khóa");
        messages_en.put("enable", "Tài khoản chưa kích hoạt");
        messages_en.put("expired", "Tài khoản đã bị hết hạn");
        messages_en.put("AbstractUserDetailsAuthenticationProvider.badCredentials", "password_valid");
        messages_en.put("password_valid", "Mật khẩu không đúng");
        messages_en.put("max_session", "Tài khoản này đã được đăng nhập từ một thiết bị khác!");
        messages_en.put("session_expired", "Phiên đăng nhập hết hạn");
        messages_en.put("order_not_found", "Đơn hàng không tồn tại");
        messages_en.put("user_not_found", "Tài khoản không tồn tại");
        messages_en.put("201", "Tạo mới thành công");
        messages_en.put("200", "Thành công");
        messages_en.put("400", "Tạo mới thất bại");
        messages_en.put("500", "Hệ thống xảy ra lỗi =))");
        messages_en.put("404", "%s không tồn tại!");
        messages_en.put("403", "Tài khoản của bạn không có quyền truy cập!");
        messages_en.put("date.format", "dd/MM/yyyy");
        messages_en.put("invalid_token", "Mã không hợp lệ");
        messages_en.put("password_no_match", "Các trường mật khẩu phải khớp nhau");
        messages_en.put("expired_token", "Mã hết hạn");
        messages_en.put("client_not_found", "Mã người dùng không tồn tại");
        messages_en.put("forgot_password_success", "Nếu địa chỉ email của bạn tồn tại trong cơ sở dữ liệu của chúng tôi, bạn sẽ nhận được liên kết khôi phục mật khẩu tại địa chỉ email của mình sau vài phút.");
        return messages_en.get(key);
    }
}
