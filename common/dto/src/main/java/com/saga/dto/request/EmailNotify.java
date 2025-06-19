package com.saga.dto.request;

import com.saga.dto.enums.EmailType;
import com.saga.dto.enums.NotifyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailNotify {
    private String id;
    private String from;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String subject;
    private String content;
    private String message;
    private boolean isHtml;
    private boolean isTemplate;
    private boolean hasAttachment;
    private String pathToAttachment;
    private String attachmentName;
    private String templateLocation;
    private Map<String, Object> parameterMap;
    private Map<String, Object> staticResourceMap;
    private NotifyStatus emailStatus;
    private EmailType emailType;
}
