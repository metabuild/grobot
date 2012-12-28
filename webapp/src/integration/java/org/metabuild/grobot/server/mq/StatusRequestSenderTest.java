/*
 * Copyright 2012 Metabuild Software, LLC. (http://www.metabuild.org)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.metabuild.grobot.server.mq;

import javax.jms.JMSException;

import org.junit.Ignore;
import org.junit.Test;
import org.metabuild.grobot.server.config.ServerJmsConfig;
import org.metabuild.grobot.server.status.StatusRequestProducer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author jburbridge
 *
 */
public class StatusRequestSenderTest {

	@Ignore("This intergration test requires that an mq broker be running")
	@Test
	public void test() throws JMSException, InterruptedException {
		ApplicationContext context = new AnnotationConfigApplicationContext(ServerJmsConfig.class); 
		StatusRequestProducer producer = context.getBean(StatusRequestProducer.class);
		int x = 0;
		while (x++ < 10) {
			producer.sendStatusRequest();
			Thread.sleep(5000);
		}
	}
}
