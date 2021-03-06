/*
 * Copyright 2019 ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package tech.pegasys.artemis.datastructures.operations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static tech.pegasys.artemis.datastructures.util.DataStructureUtil.randomUnsignedLong;

import com.google.common.primitives.UnsignedLong;
import net.consensys.cava.bytes.Bytes;
import net.consensys.cava.bytes.Bytes32;
import org.junit.jupiter.api.Test;

class AttestationDataTest {

  private UnsignedLong slot = randomUnsignedLong();
  private UnsignedLong shard = randomUnsignedLong();
  private Bytes32 beaconBlockRoot = Bytes32.random();
  private Bytes32 epochBoundaryRoot = Bytes32.random();
  private Bytes32 shardBlockRoot = Bytes32.random();
  private Bytes32 latestCrosslinkRoot = Bytes32.random();
  private UnsignedLong justifiedEpoch = randomUnsignedLong();
  private Bytes32 justifiedBlockRoot = Bytes32.random();

  private AttestationData attestationData =
      new AttestationData(
          slot,
          shard,
          beaconBlockRoot,
          epochBoundaryRoot,
          shardBlockRoot,
          latestCrosslinkRoot,
          justifiedEpoch,
          justifiedBlockRoot);

  @Test
  void equalsReturnsTrueWhenObjectAreSame() {
    AttestationData testAttestationData = attestationData;

    assertEquals(attestationData, testAttestationData);
  }

  @Test
  void equalsReturnsTrueWhenObjectFieldsAreEqual() {
    AttestationData testAttestationData =
        new AttestationData(
            slot,
            shard,
            beaconBlockRoot,
            epochBoundaryRoot,
            shardBlockRoot,
            latestCrosslinkRoot,
            justifiedEpoch,
            justifiedBlockRoot);

    assertEquals(attestationData, testAttestationData);
  }

  @Test
  void equalsReturnsFalseWhenSlotsAreDifferent() {
    AttestationData testAttestationData =
        new AttestationData(
            slot.plus(randomUnsignedLong()),
            shard,
            beaconBlockRoot,
            epochBoundaryRoot,
            shardBlockRoot,
            latestCrosslinkRoot,
            justifiedEpoch,
            justifiedBlockRoot);

    assertNotEquals(attestationData, testAttestationData);
  }

  @Test
  void equalsReturnsFalseWhenShardsAreDifferent() {
    AttestationData testAttestationData =
        new AttestationData(
            slot,
            shard.plus(randomUnsignedLong()),
            beaconBlockRoot,
            epochBoundaryRoot,
            shardBlockRoot,
            latestCrosslinkRoot,
            justifiedEpoch,
            justifiedBlockRoot);

    assertNotEquals(attestationData, testAttestationData);
  }

  @Test
  void equalsReturnsFalseWhenBeaconBlockRootsAreDifferent() {
    AttestationData testAttestationData =
        new AttestationData(
            slot,
            shard,
            beaconBlockRoot.not(),
            epochBoundaryRoot,
            shardBlockRoot,
            latestCrosslinkRoot,
            justifiedEpoch,
            justifiedBlockRoot);

    assertNotEquals(attestationData, testAttestationData);
  }

  @Test
  void equalsReturnsFalseWhenEpochBoundaryRootAreDifferent() {
    AttestationData testAttestationData =
        new AttestationData(
            slot,
            shard,
            beaconBlockRoot,
            epochBoundaryRoot.not(),
            shardBlockRoot,
            latestCrosslinkRoot,
            justifiedEpoch,
            justifiedBlockRoot);

    assertNotEquals(attestationData, testAttestationData);
  }

  @Test
  void equalsReturnsFalseWhenShardBlockRootsAreDifferent() {
    AttestationData testAttestationData =
        new AttestationData(
            slot,
            shard,
            beaconBlockRoot,
            epochBoundaryRoot,
            shardBlockRoot.not(),
            latestCrosslinkRoot,
            justifiedEpoch,
            justifiedBlockRoot);

    assertNotEquals(attestationData, testAttestationData);
  }

  @Test
  void equalsReturnsFalseWhenLatestCrosslinkRootsAreDifferent() {
    AttestationData testAttestationData =
        new AttestationData(
            slot,
            shard,
            beaconBlockRoot,
            epochBoundaryRoot,
            shardBlockRoot,
            latestCrosslinkRoot.not(),
            justifiedEpoch,
            justifiedBlockRoot);

    assertNotEquals(attestationData, testAttestationData);
  }

  @Test
  void equalsReturnsFalseWhenJustifiedEpochsAreDifferent() {
    AttestationData testAttestationData =
        new AttestationData(
            slot,
            shard,
            beaconBlockRoot,
            epochBoundaryRoot,
            shardBlockRoot,
            latestCrosslinkRoot,
            justifiedEpoch.plus(randomUnsignedLong()),
            justifiedBlockRoot);

    assertNotEquals(attestationData, testAttestationData);
  }

  @Test
  void equalsReturnsFalseWhenJustifiedBlockRootsAreDifferent() {
    AttestationData testAttestationData =
        new AttestationData(
            slot,
            shard,
            beaconBlockRoot,
            epochBoundaryRoot,
            shardBlockRoot,
            latestCrosslinkRoot,
            justifiedEpoch,
            justifiedBlockRoot.not());

    assertNotEquals(attestationData, testAttestationData);
  }

  @Test
  void rountripSSZ() {
    Bytes sszAttestationDataBytes = attestationData.toBytes();
    assertEquals(attestationData, AttestationData.fromBytes(sszAttestationDataBytes));
  }
}
