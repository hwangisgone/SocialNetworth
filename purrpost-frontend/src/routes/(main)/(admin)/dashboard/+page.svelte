<script lang='ts'>
	import ContentBody  from '../../components/post/ContentBody.svelte';
	import { getAllPosts } from '$lib/adminapi';


	let postList = [];

let dataLoaded = false;

async function loadAll() {
	postList = await getAllPosts();
	dataLoaded = true;
}

	loadAll();

</script>

<!-- Main -->
<main class="max-w-full h-full flex relative overflow-y-hidden">
  <!-- Container -->
	<div id="imageContainer" class="w-full p-4 flex flex-col  items-start bg-opacity-0 justify-start gap-4 overflow-y-scroll">
	<!-- Container -->
	{#if dataLoaded}
		<ContentBody {postList}/>
	{:else}
		<p>Fetching...</p>
	{/if}

	</div>
</main>
	  