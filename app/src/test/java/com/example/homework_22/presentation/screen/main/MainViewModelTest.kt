package com.example.homework_22.presentation.screen.main

import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.model.GetPost
import com.example.homework_22.domain.model.GetStory
import com.example.homework_22.domain.usecase.post.GetPostUseCase
import com.example.homework_22.domain.usecase.story.GetStoryUseCase
import com.example.homework_22.presentation.event.MainEvent
import com.example.homework_22.presentation.mapper.toPresenter
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {
    private lateinit var mainViewModel: MainViewModel

    @Mock
    private lateinit var getStoryUseCase: GetStoryUseCase

    @Mock
    private lateinit var getPostUseCase: GetPostUseCase

    @get:Rule
    val coroutineRule = MainCoroutine()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mainViewModel =
            MainViewModel(
                getPostUseCase = getPostUseCase,
                getStoryUseCase = getStoryUseCase
            )
    }

    @Test
    fun `when FetchStories event happens and data is fetches successfully, stories are collected`() =
        runTest {
            val stories = listOf(
                GetStory(id = 1, cover = "coverPhotoString", title = "randomTitle"),
                GetStory(id = 2, cover = "coverPhotoString", title = "randomTitle")
            )
            `when`(getStoryUseCase()).thenReturn(flowOf(Resource.Success(stories)))

            mainViewModel.onEvent(MainEvent.FetchStories)

            val collectState = mainViewModel.mainState.take(1).toList()

            assertTrue(
                "Stories expected",
                collectState.any {
                    it.stories == stories.map { it.toPresenter() }
                }
            )
        }

    @Test
    fun `when FetchStories event happens and error happens, error message is collected`() =
        runTest {
            val errorMsg = "error message"

            `when`(getStoryUseCase()).thenReturn(flowOf(Resource.Error(errorMsg)))

            mainViewModel.onEvent(MainEvent.FetchStories)

            val collectState = mainViewModel.mainState.take(1).toList()

            assertTrue(
                "error message expected",
                collectState.any { it.errorMessage == errorMsg }
            )
        }

    @Test
    fun `when FetchStories event happens, loading state occurs, loading state is collected`() =
        runTest {
            val stories = listOf(
                GetStory(id = 1, cover = "coverPhotoString", title = "randomTitle"),
                GetStory(id = 2, cover = "coverPhotoString", title = "randomTitle")
            )
            `when`(getStoryUseCase()).thenReturn(flowOf(Resource.Success(stories)))

            mainViewModel.onEvent(MainEvent.FetchStories)

            val collectState = mainViewModel.mainState.take(1).toList()

            assertEquals(false, collectState.any { it.isLoading })
        }

    @Test
    fun `when FetchPosts event happens and data is fetches successfully, posts are collected`() =
        runTest {
            val posts = listOf(
                GetPost(
                    id = 1,
                    images = listOf("string"),
                    title = "title",
                    comments = 1,
                    likes = 1,
                    shareContent = "string",
                    owner = GetPost.GetOwnerInfo(
                        firstName = "string",
                        lastName = "string",
                        profile = "string",
                        postDate = 123
                    )
                ),
                GetPost(
                    id = 2,
                    images = listOf("string", "string"),
                    title = "title",
                    comments = 1,
                    likes = 1,
                    shareContent = "string",
                    owner = GetPost.GetOwnerInfo(
                        firstName = "string",
                        lastName = "string",
                        profile = "string",
                        postDate = 123
                    )
                )
            )
            `when`(getPostUseCase()).thenReturn(flowOf(Resource.Success(posts)))

            mainViewModel.onEvent(MainEvent.FetchPosts)

            val collectState = mainViewModel.mainState.take(1).toList()

            assertTrue(
                "Stories expected",
                collectState.any {
                    it.posts == posts.map { it.toPresenter() }
                }
            )
        }

    @Test
    fun `when FetchPosts event happens and error happens, error message is collected`() =
        runTest {
            val errorMsg = "error message"

            `when`(getPostUseCase()).thenReturn(flowOf(Resource.Error(errorMsg)))

            mainViewModel.onEvent(MainEvent.FetchPosts)

            val collectState = mainViewModel.mainState.take(1).toList()

            assertTrue(
                "error message expected",
                collectState.any { it.errorMessage == errorMsg }
            )
        }

    @Test
    fun `when FetchPosts event happens, loading state occurs, loading state is collected`() =
        runTest {
            val posts = listOf(
                GetPost(
                    id = 1,
                    images = listOf("string"),
                    title = "title",
                    comments = 1,
                    likes = 1,
                    shareContent = "string",
                    owner = GetPost.GetOwnerInfo(
                        firstName = "string",
                        lastName = "string",
                        profile = "string",
                        postDate = 123
                    )
                ),
                GetPost(
                    id = 2,
                    images = listOf("string", "string"),
                    title = "title",
                    comments = 1,
                    likes = 1,
                    shareContent = "string",
                    owner = GetPost.GetOwnerInfo(
                        firstName = "string",
                        lastName = "string",
                        profile = "string",
                        postDate = 123
                    )
                )
            )
            `when`(getPostUseCase()).thenReturn(flowOf(Resource.Success(posts)))

            mainViewModel.onEvent(MainEvent.FetchPosts)

            val collectState = mainViewModel.mainState.take(1).toList()

            assertEquals(false, collectState.any { it.isLoading })
        }

    @Test
    fun `ResetErrorMessage event happens and error message becomes null`() = runTest {
        val errorMsg = "error message"

        `when`(getPostUseCase()).thenReturn(flowOf(Resource.Error(errorMsg)))

        mainViewModel.onEvent(MainEvent.ResetErrorMessage)

        val collectState = mainViewModel.mainState.take(1).toList()

        assertTrue(
            "reset error",
            collectState.any { it.errorMessage == null }
        )

    }

}