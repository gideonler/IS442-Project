<template>
  <div class="card mb-3" :class="[type && `card-${type}`]">
    <div class="card-image" v-if="$slots.image">
      <slot name="image"></slot>
    </div>
    <div class="card-header" v-if="$slots.header || title" :class="headerClasses">
      <slot name="header">
        <h3 class="card-title">{{title}}</h3>
        <p class="card-category" v-if="subTitle">{{subTitle}}</p>
      </slot>
    </div>
    <div class="card-body" v-if="$slots.default" :class="bodyClasses">
      <slot></slot>
    </div>
    <slot name="raw-content"></slot>
    <div class="card-footer" :class="footerClasses" v-if="$slots.footer">
      <slot name="footer"></slot>
    </div>
  </div>
</template>
<script>
  export default {
    name: "card-component",
    props: {
      title: {
        type: String,
        description: "Card title"
      },
      subTitle: {
        type: String,
        description: "Card subtitle"
      },
      type: {
        type: String,
        description: "Card type (e.g primary/danger etc)"
      },
      headerClasses: {
        type: [String, Object, Array],
        description: "Card header css classes"
      },
      bodyClasses: {
        type: [String, Object, Array],
        description: "Card body css classes"
      },
      footerClasses: {
        type: [String, Object, Array],
        description: "Card footer css classes"
      }
    }
  };
</script>
<style>
</style>
