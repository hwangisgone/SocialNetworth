<script lang='ts'>
	import ContentBody  from '../../components/post/ContentBody.svelte';
	import { page } from '$app/stores';

	import { getPost, getHotPosts } from '$lib/postapi';

// $page.params.postid

	let dataLoaded = false;
	let replyList = [];
	let currentPost = {};

async function loadAll() {
	replyList = await getHotPosts();
	currentPost = await getPost($page.params.postid);
	dataLoaded = true;
}

	loadAll();
</script>

<!-- Main -->
<main class="max-w-full h-full flex relative overflow-y-hidden">
  <!-- Container -->
	<div id="imageContainer" class="w-full m-4 flex flex-col  items-start bg-opacity-0 justify-start gap-4 overflow-y-scroll">
	<!-- Container -->
	{#if dataLoaded}
		<ContentBody postList={replyList}/>
	{:else}
		<p>Fetching...</p>
	{/if}

	</div>
</main>