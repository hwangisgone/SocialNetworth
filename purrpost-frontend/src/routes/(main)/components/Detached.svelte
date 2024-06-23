<script lang="ts">
	export let text = "Title";
	export let wStart = 0;
	export let hStart = 0;

	let moving = false;
	let left = window.innerWidth * wStart;
	let top = window.innerHeight * hStart;

	function onMouseDown() {
		moving = true;
	}
	
	function onMouseMove(e) {
		if (moving) {
			left += e.movementX;
			top += e.movementY;
		}
	}
	
	function onMouseUp() {
		moving = false;
	}

	let collapse = true;

	import { slide } from 'svelte/transition';
</script>

<svelte:window on:mouseup={onMouseUp} on:mousemove={onMouseMove} />

<div class="absolute z-10 drag-mouse m-4" style="left: {left}px; top: {top}px;" on:mousedown={onMouseDown}>
	<slot />
</div>

<style>
	.drag-mouse {
		user-select: none;
		cursor: move;
/*		position: absolute;*/
	}
</style>