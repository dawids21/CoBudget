package xyz.stasiak.cobudgetbackend.entry.domain.dto;

import javax.validation.constraints.NotBlank;

record EntryCategoryWriteDTO(@NotBlank(message = "name is mandatory") String name) {

}
