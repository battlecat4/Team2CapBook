package com.cg.project.daoservices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.project.beans.Photos;

public interface PhotosDAO extends JpaRepository<Photos, String> {

}
