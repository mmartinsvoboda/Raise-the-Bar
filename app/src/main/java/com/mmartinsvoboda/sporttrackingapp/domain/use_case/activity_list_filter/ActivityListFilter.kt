package com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_list_filter

enum class ActivityListFilter(
    val _name: String
) {
    ALL("All"),
    SYNCED("Synced"),
    LOCAL("Local only")
}