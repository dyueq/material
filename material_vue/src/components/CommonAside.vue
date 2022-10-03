<template>
    <el-menu class="el-menu-vertical-demo" :collapse="isCollapse" background-color="#545c64" text-color="#fff"
        active-text-color="#ffd04b">
        <h3 v-show="!isCollapse">疫情物资慈善捐赠管理系统</h3>
        <h3 v-show="isCollapse">捐赠</h3>
        <el-menu-item :index="item.menuPath" v-for="item in noChildren" :key="item.menuPath" @click="clickMenu(item)">
            <i :class="'el-icon-' + item.menuIcon"></i>
            <span slot="title">{{ item.menuLabel }}</span>
        </el-menu-item>
        <el-submenu :index="item.menuLabel" v-for="item in hasChildren" :key="item.menuPath">
            <template slot="title">
                <i :class="'el-icon-' + item.menuIcon"></i>
                <span slot="title">{{ item.menuLabel }}</span>
            </template>
            <el-menu-item-group>
                <el-menu-item :index="subItem.menuPath" v-for="(subItem, subIndex) in item.children" :key="subIndex"
                    @click="clickMenu(subItem)">
                    <i :class="'el-icon-' + subItem.menuIcon"></i>
                    <span slot="title">{{ subItem.menuLabel }}</span>
                </el-menu-item>
            </el-menu-item-group>
        </el-submenu>
    </el-menu>
</template>
<script>
export default {
    name: "CommonAside",
    data() {
        return {
            menu: [],
        };
    },
    created() {
        this.initMenu()
        this.$router.push({ path: "/home" })
    },
    methods: {

        initMenu() {
            this.$axios.get("/getMenu")
                .then((res) => {
                    this.menu = res.data
                    if (this.menu.children = []) {
                        this.menu.children = null
                    }
                    // console.log(res);
                })
        },

        clickMenu(item) {
            this.$router.push({ name: item.menuName });
            this.$store.commit("selectMenu", item);
        },
    },
    computed: {
        noChildren() {
            return this.menu.filter((item) => !item.children);
        },
        hasChildren() {
            return this.menu.filter((item) => item.children);
        },
        isCollapse() {
            return this.$store.state.tab.isCollapse;
        },
    },
};
</script>
<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 12.5rem;
    min-height: 53.125rem;
}

.el-menu--collapse {
    min-height: 53.125rem;
}

h3 {
    color: aqua;
    font-size: 1rem;
    text-align: center;
}
</style>