<script lang='ts'>
	import Post		from './components/post/Post.svelte';
	// import CommentContainer		from './components/CommentContainer.svelte';
	// import EditProfile	from './components/EditProfileCard.svelte';
	// import ProfileCard		from './components/ProfileCard.svelte';

	import toast from 'svelte-french-toast';
	import { goto } from '$app/navigation';

	let postList = [{
		content: "Lorem ipsum dolor sit amet, consectetur",
		timePosted: "2024-06-18T01:27:33.5960633+08:00",
		timeEdited: null,
		likeCount: 0,
		replyCount: 0,
		shareCount: 0,
	}];

async function getAllPosts() {
	// Define the login data
	try {
		// Make the fetch request
		const response = await fetch('http://localhost:8081/api/allposts', {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
				'Authorization': localStorage.getItem('authToken'),
			},
		});

		// Check if the response is ok (status code in the range 200-299)
		if (response.status === 200) {
			postList = await response.json();
			console.log(postList);
		} else if (response.status === 401) {
			// Redirect back to login
			toast.error("Login required");
			goto("/login");
		}
	} catch (error) {
		toast.error(error);
		console.error(error);
	}
}

	getAllPosts();
</script>

{#each postList as post}
	<Post {post}/>
{/each}
<!-- <CommentContainer />
<ProfileCard />
<EditProfile /> -->
