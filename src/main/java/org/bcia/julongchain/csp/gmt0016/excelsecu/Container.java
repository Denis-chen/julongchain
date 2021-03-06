/**
 * Copyright BCIA. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.bcia.julongchain.csp.gmt0016.excelsecu;

/**
 * GM0016 容器类
 *
 * @author chenhao
 * @date 2018/4/2
 * @company Excelsecu
 */
public class Container {


    private String containerName;

    private int keyCount;

    private long containerHandle;

    public Container(String containerName, long containerHandle) {
        this.containerName = containerName;
        this.containerHandle = containerHandle;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public int getKeyCount() {
        return keyCount;
    }

    public void setKeyCount(int keyCount) {
        this.keyCount = keyCount;
    }

    public long getContainerHandle() {
        return containerHandle;
    }

    public void setContainerHandle(long containerHandle) {
        this.containerHandle = containerHandle;
    }
}
