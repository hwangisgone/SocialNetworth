<script>
	import { page } from '$app/stores';
	import { goto } from '$app/navigation';

	import { myReactionMap } from '$lib/stores';

	import ContentBody  from '../../ContentBody.svelte';

	import ProfileCard  from './ProfileCard.svelte';

	let postList = [];
	let currentUser = {};

async function getUser() {
	try {
		const response = await fetch('http://localhost:8081/api/user/' + $page.params.userid, {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
				'Authorization': localStorage.getItem('authToken'),
			},
		});

		// Check if the response is ok (status code in the range 200-299)
		if (response.status === 200) {
			currentUser = await response.json();
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

async function getAllUserPosts() {
	try {
		const response = await fetch('http://localhost:8081/api/user/' + $page.params.userid + '/allposts', {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
				'Authorization': localStorage.getItem('authToken'),
			},
		});

		// Check if the response is ok (status code in the range 200-299)
		if (response.status === 200) {
			postList = await response.json();

			postList.forEach(post => {
				post.liked = $myReactionMap[post.id] ? true : false;
				post.user = currentUser;
			})
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

let dataLoaded = false;
async function loadData() {
	await getUser();
	await getAllUserPosts();
	dataLoaded = true;
}

	loadData();
</script>


<!-- Main -->
<main class="max-w-full h-full flex relative overflow-y-hidden">
  <!-- Container -->

	<div id="imageContainer" class="w-full m-4 flex flex-col items-start bg-opacity-0 justify-start gap-4 overflow-y-scroll">
	   	<ProfileCard/>
	<!-- Container -->
	{#if dataLoaded}
		<ContentBody {postList}/>
	{:else}
		<p>Fetching...</p>
	{/if}

	</div>
</main>