package com.calendar.calendarapi.tag;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Optional<Set<Tag>> getAllTags() {
        return Optional.of((Set<Tag>) tagRepository.findAll());
    }

    public Optional<Tag> addTag(Tag tag) {
        return Optional.of(tagRepository.save(tag));
    }

    public Optional<Tag> updateTag(Tag tag) {
        return Optional.of(tagRepository.save(tag));
    }

    public void deleteTag(Tag tag) {
        tagRepository.delete(tag);
    }

    public void deleteTagById(long id) {
        tagRepository.deleteById(id);
    }

    public Optional<Tag> getTagById(long id) {
        return tagRepository.findById(id);
    }

}
