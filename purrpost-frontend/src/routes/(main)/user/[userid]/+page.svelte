<script>
	import ContentBody  from '../../components/post/ContentBody.svelte';
	import ProfileCard  from './ProfileCard.svelte';


	import { page } from '$app/stores';
	import { getUser } from '$lib/userapi';
	import { getAllUserPosts } from '$lib/postapi';



	let dataLoaded = false;
	let postList = [];
	let currentUser = {};


async function loadAll() {
	currentUser = await getUser($page.params.userid);
	postList = await getAllUserPosts($page.params.userid);
	
	dataLoaded = true;
}

	loadAll();
</script>


<!-- Main -->
<main class="max-w-full h-full flex relative overflow-y-hidden">
  <!-- Container -->

	<div class="w-full m-4 flex flex-col items-start bg-opacity-0 justify-start gap-4 overflow-y-scroll">
	   	<ProfileCard {currentUser}/>
	<!-- Container -->
	{#if dataLoaded}
		<ContentBody {postList}/>
	{:else}
		<p>Fetching...</p>
	{/if}

	</div>
</main>