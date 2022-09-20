package com.example.schoolsapp.model


import com.google.gson.annotations.SerializedName

data class SchoolItem(
    @SerializedName("addtl_info1")
    val addtlInfo1: String? = null,
    @SerializedName("attendance_rate")
    val attendanceRate: String? = null,
    @SerializedName("bbl")
    val bbl: String? = null,
    @SerializedName("bin")
    val bin: String? = null,
    @SerializedName("boro")
    val boro: String? = null,
    @SerializedName("borough")
    val borough: String? = null,
    @SerializedName("boys")
    val boys: String? = null,
    @SerializedName("building_code")
    val buildingCode: String? = null,
    @SerializedName("bus")
    val bus: String? = null,
    @SerializedName("campus_name")
    val campusName: String? = null,
    @SerializedName("census_tract")
    val censusTract: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("dbn")
    val dbn: String? = null,
    @SerializedName("diplomaendorsements")
    val diplomaendorsements: String? = null,
    @SerializedName("earlycollege")
    val earlycollege: String? = null,
    @SerializedName("ell_programs")
    val ellPrograms: String? = null,
    @SerializedName("end_time")
    val endTime: String? = null,
    @SerializedName("extracurricular_activities")
    val extracurricularActivities: String? = null,
    @SerializedName("fax_number")
    val faxNumber: String? = null,
    @SerializedName("finalgrades")
    val finalgrades: String? = null,
    @SerializedName("geoeligibility")
    val geoeligibility: String? = null,
    @SerializedName("girls")
    val girls: String? = null,
    @SerializedName("grades2018")
    val grades2018: String? = null,
    @SerializedName("graduation_rate")
    val graduationRate: String? = null,
    @SerializedName("international")
    val international: String? = null,
    @SerializedName("language_classes")
    val languageClasses: String? = null,
    @SerializedName("latitude")
    val latitude: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("longitude")
    val longitude: String? = null,
    @SerializedName("neighborhood")
    val neighborhood: String? = null,
    @SerializedName("nta")
    val nta: String? = null,
    @SerializedName("overview_paragraph")
    val overviewParagraph: String? = null,
    @SerializedName("pbat")
    val pbat: String? = null,
    @SerializedName("pct_stu_enough_variety")
    val pctStuEnoughVariety: String? = null,
    @SerializedName("pct_stu_safe")
    val pctStuSafe: String? = null,
    @SerializedName("phone_number")
    val phoneNumber: String? = null,
    @SerializedName("primary_address_line_1")
    val primaryAddressLine1: String? = null,
    @SerializedName("program1")
    val program1: String? = null,
    @SerializedName("psal_sports_boys")
    val psalSportsBoys: String? = null,
    @SerializedName("psal_sports_coed")
    val psalSportsCoed: String? = null,
    @SerializedName("psal_sports_girls")
    val psalSportsGirls: String? = null,
    @SerializedName("ptech")
    val ptech: String? = null,
    @SerializedName("school_10th_seats")
    val school10thSeats: String? = null,
    @SerializedName("school_accessibility_description")
    val schoolAccessibilityDescription: String? = null,
    @SerializedName("school_email")
    val schoolEmail: String? = null,
    @SerializedName("school_name")
    val schoolName: String? = null,
    @SerializedName("school_sports")
    val schoolSports: String? = null,
    @SerializedName("shared_space")
    val sharedSpace: String? = null,
    @SerializedName("specialized")
    val specialized: String? = null,
    @SerializedName("start_time")
    val startTime: String? = null,
    @SerializedName("state_code")
    val stateCode: String? = null,
    @SerializedName("subway")
    val subway: String? = null,
    @SerializedName("total_students")
    val totalStudents: String? = null,
    @SerializedName("transfer")
    val transfer: String? = null,
    @SerializedName("website")
    val website: String? = null,
    @SerializedName("zip")
    val zip: String? = null
)