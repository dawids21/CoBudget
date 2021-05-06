package xyz.stasiak.cobudgetbackend.entry.domain.dto;

import javax.validation.constraints.NotBlank;

record EntryExpenseSubcategoryWriteDTO(@NotBlank(message = "name is mandatory") String name) {

}
