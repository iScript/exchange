<template>
    <div>
        <!--委托列表-->
        <el-table
                :data="
                    tableData.slice
                    (
                           (query.currentPage - 1) * query.pageSize,
                           query.currentPage * query.pageSize
                    )
                "
                border
                :cell-style="cellStyle"
                @sort-change="changeTableSort"
                :default-sort="{prop : 'time',order:'descending'}"
        >
            <!--委托时间 股票代码 名称 委托价格 委托数量 方向 状态-->
            <el-table-column prop="time" label="委托时间" align="center"
                             sortable :sort-orders="['ascending', 'descending']"/>
            <el-table-column prop="code" label="股票代码" :formatter="codeFormatter" align="center"/>
            <el-table-column prop="name" label="名称" align="center"/>
            <el-table-column prop="price" label="委托价格" :formatter="priceFormatter" align="center"/>
            <el-table-column prop="ocount" label="委托数量" align="center"/>
            <el-table-column prop="direction" label="方向" :formatter="directionFormatter" align="center"/>
            <el-table-column prop="status" label="状态" :formatter="statusFormatter" align="center"/>
            <!-- 撤单按钮-->
        </el-table>

        <!--分页控件+刷新-->
        <div class="pagination">
            <el-button round
                       type="primary" size="mini"
                       style="margin-top:2px;float: right"
                       icon="el-icon-refresh"
                       @click="queryOrder">
                刷新
            </el-button>
            <el-pagination
                    background
                    layout="total, prev, pager, next"
                    :current-page="query.currentPage"
                    :page-size="query.pageSize"
                    :total="dataTotalCount"
                    @current-change="handlePageChange"/>
        </div>


    </div>
</template>

<script>

    import {queryOrder, queryBalance} from "../api/orderApi";
    import {codeFormat, moneyFormat, directionFormat, statusFormat} from "../api/formatter";

    export default {
        name: "OrderList",
        data() {
            return {
                tableData: [],
                query: {
                    currentPage: 1, // 当前页码
                    pageSize: 4 // 每页的数据条数
                }
            };
        },
        computed: {
            orderData() {
                return this.$store.state.orderData;
            },
            dataTotalCount() {
                return this.$store.state.orderData.length;
            }
        },
        watch: {
            orderData: function (val) {
                this.tableData = val;
            }
        },
        created() {
            this.tableData = this.orderData;
        },
        methods: {
            queryOrder() {
                queryOrder();
                queryBalance();
            },
            codeFormatter(row, column) {
                return codeFormat(row.code);
            },
            priceFormatter(row, column) {
                return moneyFormat(row.price);
            },
            directionFormatter(row, column) {
                console.log(row);
                return directionFormat(row.direction);
            },
            // 禁用状态格式化
            statusFormatter(row, column) {
                // 委托状态：// 0.已报  1.已成 2.部成 3.废单 4.已撤
                return statusFormat(row.status);
            },

            handlePageChange(val) {
                // this.query.currentPage = val;
                this.$set(this.query, 'currentPage', val);
            },
            cellStyle({row, column, rowIndex, columnIndex}) {
                return "padding:2px";
            },
            changeTableSort(column) {
                let fieldName = column.prop;
                let sortingType = column.order;
                if (fieldName === 'time') {
                    if (sortingType == "descending") {
                        this.tableData = this.tableData.sort((a, b) => {
                                if (b[fieldName] > a[fieldName]) {
                                    return 1;
                                } else if (b[fieldName] === a[fieldName]) {
                                    return 0;
                                } else {
                                    return -1;
                                }
                            }
                        );
                    } else {
                        this.tableData = this.tableData.sort((a, b) => {
                                if (b[fieldName] > a[fieldName]) {
                                    return -1;
                                } else if (b[fieldName] === a[fieldName]) {
                                    return 0;
                                } else {
                                    return 1;
                                }
                            }
                        );
                    }
                }
            }
        }
    }
</script>

<style scoped>

</style>