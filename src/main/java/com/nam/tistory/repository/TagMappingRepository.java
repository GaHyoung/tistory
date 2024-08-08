package com.nam.tistory.repository;

import com.nam.tistory.entity.TagMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagMappingRepository extends JpaRepository<TagMapping, TagMapping.Pk> {
}
