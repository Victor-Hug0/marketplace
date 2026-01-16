package br.com.victor.Marketplace.entity.enums;

public enum MimeType {
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    IMAGE_GIF("image/gif");

    private final String type;

    MimeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
