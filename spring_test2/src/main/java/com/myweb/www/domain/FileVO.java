package com.myweb.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FileVO {
	/*
	 * create table file(
		uuid varchar(256) not null,
		save_dir varchar(256) not null,
		file_name varchar(256) not null,
		file_type tinyint(1) default 0,
		bno bigint,
		file_size bigint,
		reg_at datetime default now(),
		primary key(uuid));
	 * */
	
	private String uuid;
	private String saveDir;
	private String fileName;
	private int fileType;
	private long bno;
	private long fileSize;
	private String regAt;

}
