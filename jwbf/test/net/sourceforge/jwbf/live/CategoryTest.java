/*
 * Copyright 2007 Thomas Stock.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Contributors:
 * 
 */
package net.sourceforge.jwbf.live;


import java.util.Iterator;

import net.sourceforge.jwbf.bots.MediaWikiBot;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * 
 * @author Thomas Stock
 *
 */
public class CategoryTest extends LiveTestFather {

	
	private MediaWikiBot bot = null;
	/**
	 * Setup log4j.
	 * @throws Exception a
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		PropertyConfigurator.configureAndWatch("test4log4j.properties",
				60 * 1000);
	}
	

	/**
	 * Test category read.
	 * @throws Exception a
	 */
	@Test
	public final void categoryWikipediaDe() throws Exception {
		
		bot = new MediaWikiBot("http://de.wikipedia.org/w/index.php");
		Iterator<String> is = bot.getCategoryMembers(getValue("category_category")).iterator();
		int i = 0;
		while (is.hasNext()) {
			is.next();
			i++;
			if (i > 50) {
				break;
			}
		}
		Assert.assertTrue(i > getIntValue("category_category_count"));
	}
	
	

	/**
	 * Test category read.
	 * @throws Exception a
	 */
	@Test
	public final void categoryCustomWiki() throws Exception {
		
		bot = new MediaWikiBot(getValue("category_customWiki_url"));
		bot.login(getValue("category_customWiki_user"), getValue("category_customWiki_pass"));
		Iterator<String> is = bot.getCategoryMembers(getValue("category_customWiki_category")).iterator();
		int i = 0;
		while (is.hasNext()) {
			is.next();
			i++;
			if (i > 50) {
				break;
			}
		}
		Assert.assertTrue(i > getIntValue("category_customWiki_category_count"));
	}
}