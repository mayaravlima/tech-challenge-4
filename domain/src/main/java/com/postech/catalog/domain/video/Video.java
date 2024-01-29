package com.postech.catalog.domain.video;

import com.postech.catalog.domain.AggregateRoot;
import com.postech.catalog.domain.catagory.CategoryID;
import com.postech.catalog.domain.utils.InstantUtils;
import com.postech.catalog.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Video extends AggregateRoot<VideoID> {

    private String title;
    private String description;
    private String url;
    private Instant createdAt;
    private Set<CategoryID> categories;

    protected Video(
            final VideoID id,
            final String title,
            final String description,
            final String url,
            final Instant createdAt,
            final Set<CategoryID> categories
    ) {
        super(id);
        this.title = title;
        this.description = description;
        this.url = url;
        this.createdAt = createdAt;
        this.categories = categories;
    }

    public Video update(
            final String title,
            final String description,
            final String url,
            final Set<CategoryID> categories
    ) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.setCategories(categories);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Set<CategoryID> getCategories() {
        return categories != null ? Collections.unmodifiableSet(categories) : Collections.emptySet();
    }

    private void setCategories(final Set<CategoryID> categories) {
        this.categories = categories != null ? new HashSet<>(categories) : Collections.emptySet();
    }

    public static Video newVideo(
            final String title,
            final String description,
            final String url,
            final Set<CategoryID> categories
    ) {
        final var now = InstantUtils.now();
        final var id = VideoID.unique();
        return new Video(
                id,
                title,
                description,
                url,
                now,
                categories
        );
    }

    public static Video with(final Video video) {
        return new Video(
                video.getId(),
                video.getTitle(),
                video.getDescription(),
                video.getUrl(),
                video.getCreatedAt(),
                new HashSet<>(video.getCategories())
        );
    }

    public static Video with(
            final VideoID id,
            final String title,
            final String description,
            final String url,
            final Instant createdAt,
            final Set<CategoryID> categories
    ) {
        return new Video(
                id,
                title,
                description,
                url,
                createdAt,
                categories
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new VideoValidator(this, handler).validate();
    }
}
