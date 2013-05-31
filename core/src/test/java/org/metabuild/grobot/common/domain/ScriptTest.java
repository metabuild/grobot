/*
 * Copyright 2013 Metabuild Software, LLC. (http://www.metabuild.org)
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
package org.metabuild.grobot.common.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;

import org.junit.Test;

/**
 * @author jburbridge
 * @since 5/31/2013
 */
public class ScriptTest {

	@Test
	public void testConstructor() {
		Script script = new Script("path","body");
		assertEquals("path", script.getPath());
		assertEquals("body", script.getBody());
	}
	
	@Test
	public void testFileConstructor() {
		File file = mock(File.class);
		when(file.getAbsolutePath()).thenReturn("foo");
		Script script = new Script(file);
		assertEquals("foo", script.getPath());
	}
}
