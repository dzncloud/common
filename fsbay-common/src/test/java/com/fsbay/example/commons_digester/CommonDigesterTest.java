package com.fsbay.example.commons_digester;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.digester.Digester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import com.fsbay.example.model.Book;
import com.fsbay.example.model.Chapter;
import com.fsbay.example.model.Child;
import com.fsbay.example.model.Parent;
import com.fsbay.example.model.Reader;
import com.fsbay.example.model.School;
import com.fsbay.example.utils.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
public class CommonDigesterTest extends TestCase {
	public void setUp() throws Exception {
		super.setUp();
	}

	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void readerTest() {
		// 建立一个Digester对象
		Digester digester = new Digester();
		// 从reader标签开始解析,并新建一个Reader对象做为根对象
		digester.addObjectCreate("reader", Reader.class);
		// 根据reader标签属性值配置对象的属性,此类的属性必须具有setter
		digester.addSetProperties("reader");

		// -----第1层元素开始
		digester.addObjectCreate("reader/book", Book.class);
		// digester.addSetProperties("reader/book");
		// 可以用以下三条语句代替
		digester.addCallMethod("reader/book", "setInfo", 2);
		digester.addCallParam("reader/book", 0, "title");
		digester.addCallParam("reader/book", 1, "author");

		// -----第2层元素开始
		digester.addObjectCreate("reader/book/chapter", Chapter.class);
		digester.addBeanPropertySetter("reader/book/chapter/no");
		// digester.addBeanPropertySetter("reader/book/chapter/caption");
		// 同样可以替代
		digester.addCallMethod("reader/book/chapter/caption", "setCaption", 0);

		digester.addSetNext("reader/book/chapter", "addReadedChapter");
		// -----第2层元素结束

		digester.addSetNext("reader/book", "addBook");
		// -----第1层元素结束

		try {

			// 解析XML文件,并得到ROOT元素
			Reader reader = (Reader) digester.parse(new File(Utils
					.getTestResPath("/digester/readbook.xml")));
			// 可以使用的替代方法1
			// reader = (Reader)digester.getRoot();
			// 可以使用的方法2
			// 先新建一个Reader对象,再对digester.push(reader);

			System.out.println("姓名:" + reader.getName());
			System.out.println("共读书:" + reader.getBooks().size() + "本");
			System.out.println("*****************************");

			Iterator books = reader.getBooks().iterator(), chapters = null;
			Book book = null;
			Chapter chapter = null;
			while (books.hasNext()) {
				// 显示书的信息
				book = (Book) books.next();
				System.out.println("书名:" + book.getTitle() + "作者:"
						+ book.getAuthor());
				System.out.println("------------------------------");

				// 显示已阅读章节
				System.out
						.println("共读" + book.getReadedChapters().size() + "章");
				chapters = book.getReadedChapters().iterator();
				while (chapters.hasNext()) {
					chapter = (Chapter) chapters.next();
					System.out.println("第" + chapter.getNo() + "章"
							+ chapter.getCaption());
				}
				System.out.println("------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void rootTest() {

		Digester digester = new Digester();
		// digester.setValidating(false);
		digester.addObjectCreate("root", ArrayList.class);// create an ArrayList
															// when got a
															// ‘/root’ element
		digester.addObjectCreate("root/parent", Parent.class);
		digester.addObjectCreate("root/parent/child", Child.class);

		digester.addSetProperties("root/parent", "pname", "name");
		digester.addSetProperties("root/parent/child", "cname", "name");// set
																		// propertiy:cname
																		// to
																		// Child.name
		digester.addBeanPropertySetter("root/parent/child", "value");// set node
																		// value
																		// to
																		// Child.value

		digester.addSetNext("root/parent", "add");// use add() method of List
		digester.addSetNext("root/parent/child", "addChild");

		try {
			List<Parent> parents = (ArrayList<Parent>) digester.parse(new File(
					Utils.getTestResPath("/digester/root.xml")));
			System.out.println(parents.get(0).getChilds().get(0).getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void schoolTest() throws IOException, SAXException {

		Digester digester = new Digester();
		digester.addObjectCreate("school", "com.fsbay.example.model.School");
		digester.addSetProperties("school");
		digester.addObjectCreate("school/department",
				"com.fsbay.example.model.Department");
		digester.addSetProperties("school/department");
		digester.addSetNext("school/department", "addDepartment");
		digester.addObjectCreate("school/department/student",
				"com.fsbay.example.model.Student");
		digester.addSetNext("school/department/student", "addStudent");
		digester.addSetProperties("school/department/student");
		School school = (School) digester.parse(new File(Utils
				.getTestResPath("/digester/school.xml")));
		System.out.println(school);

	}
}
