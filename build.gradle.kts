plugins {
    id("dev.frozenmilk.teamcode") version "12.0.0-1.2.3"
    id("dev.frozenmilk.sinister.sloth.load") version "0.3.2"
}

repositories {
    mavenCentral()
    google() // Needed for androidx

    maven { url = uri("https://maven.brott.dev/") }
    maven { url = uri("https://repo.dairy.foundation/releases") }
    maven { url = uri("https://repo.dairy.foundation/snapshots") }
    maven { url = uri("https://central.sonatype.com/repository/maven-snapshots/") }
}

ftc {
    kotlin()

    sdk.TeamCode()

    next.v2 {
        implementation(robot("0.3.0"))
    }

    dairy {
        implementation(Sloth)
        implementation(slothboard)
    }
}

dependencies {
    implementation("com.pedropathing:revhub:3.0.2")
    implementation("com.pedropathing:tuning:1.0.3")
    implementation("com.pedropathing.ivy:pedro:1.1.1")
    implementation("dev.nextftc.v2:pedro:0.3.1-Pedro-SNAPSHOT")
}
