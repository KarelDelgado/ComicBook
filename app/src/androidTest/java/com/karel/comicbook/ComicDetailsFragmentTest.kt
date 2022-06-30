package com.karel.comicbook

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.karel.comicbook.testing.EspressoIdlingResource
import com.karel.comicbook.testing.TestData
import com.karel.comicbook.ui.view.COMIC_ID
import com.karel.comicbook.ui.view.ComicDetailsFragment
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ComicDetailsFragmentTest {

    @Before
    fun before() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun after() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun test_ComicTitleAndDescription() {
        val comic = TestData.comicBookDetailsList.first()
        val bundle = bundleOf(Pair(COMIC_ID, comic.id))
        launchFragmentInContainer (bundle, themeResId = R.style.Theme_ComicBook) { ComicDetailsFragment() }

        onView(withId(R.id.comic_book_title)).check(matches(withText(comic.title)))
        onView(withId(R.id.comic_book_description)).check(matches(withText(comic.description)))
    }
}