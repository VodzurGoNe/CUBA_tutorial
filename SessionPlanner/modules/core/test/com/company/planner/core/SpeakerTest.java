package com.company.planner.core;

import com.company.planner.PlannerTestContainer;
import com.company.planner.entity.Speaker;
import com.haulmont.cuba.core.entity.contracts.Id;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.*;
// See https://doc.cuba-platform.com/manual-7.2/integration_tests_mw.html

class SpeakerTest {

    @RegisterExtension
    public static PlannerTestContainer cont = PlannerTestContainer.Common.INSTANCE;
    static DataManager dataManager;

    @BeforeAll
    static void beforeAll() {
        dataManager = AppBeans.get(DataManager.class);
    }

    @AfterAll
    public static void afterAll() throws Exception {
    }

    @Test
    void bothSpeakersShouldEquals() {
        Speaker speaker = cont.metadata().create(Speaker.class);
        speaker.setFirstName("TestName");
        speaker.setEmail("email@haulmont.com");
        Speaker committedSpeaker = dataManager.commit(speaker);
        assertEquals(speaker, committedSpeaker);
        Speaker loadedSpeaker = dataManager.load(Id.of(speaker)).one();
        assertEquals(speaker, loadedSpeaker);
        //dataManager.remove(loadedSpeaker);
        cont.deleteRecord(loadedSpeaker);
    }
}