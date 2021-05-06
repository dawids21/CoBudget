package xyz.stasiak.cobudgetbackend.entry.domain.dto;

import javax.validation.constraints.NotBlank;

record EntryEarningCategoryWriteDTO(@NotBlank(message = "name is mandatory") String name) {

}
